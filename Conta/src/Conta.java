import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class Conta {

    private BigDecimal saldo;
    private final BigDecimal chequeEspecial;

    private boolean usaChequeEspecial = false;
    private BigDecimal valorChequeEspecialUsado = ZERO;

    private final BigDecimal definirChequeEspecial(BigDecimal saldoInicial){
        final BigDecimal QUINHENTOS = BigDecimal.valueOf(500);
        boolean condicao = QUINHENTOS.compareTo(saldoInicial) < 0; //cheque de 50%
        if (condicao){
            return saldoInicial.multiply(BigDecimal.valueOf(0.5));
        }

        return BigDecimal.valueOf(50);
    }

    public Conta(BigDecimal saldoInicial){
        this.saldo = saldoInicial;
        this.chequeEspecial = definirChequeEspecial(saldoInicial);
    }

    public BigDecimal getSaldo(){
        return this.saldo;
    }

    public void setSaldo(BigDecimal novoSaldo){
        this.saldo = novoSaldo;
    }

    public BigDecimal getChequeEspecial(){
        return this.chequeEspecial;
    }

    public void depositar(BigDecimal valor){
        BigDecimal saldoAtual = this.saldo;
        this.saldo = saldoAtual.add(valor);
    }

    public boolean sacar(BigDecimal valor){
        BigDecimal saldoAtual = this.saldo;

        if(saldoAtual.compareTo(valor) < 0){ //Precisa do cheque especial

            BigDecimal diferenca = valor.subtract(saldoAtual);
            BigDecimal valorDisponivelChequeEspecial = chequeEspecial.subtract(valorChequeEspecialUsado);

            if(diferenca.compareTo(valorDisponivelChequeEspecial) <= 0){ //Se o valor que precisa para completar puder ser emprestado
                this.saldo = ZERO;
                this.valorChequeEspecialUsado = this.valorChequeEspecialUsado.subtract(diferenca); //Usou a diferença
                this.usaChequeEspecial = true;
                return true;
            }else
            {
                return false;
            }
        }else{
            this.saldo = saldoAtual.subtract(valor);
            return true;
        }

    }

    public boolean getUsaChequeEspecial(){
        return this.usaChequeEspecial;
    }

    public BigDecimal getValorChequeEspecialUsado(){
        return this.valorChequeEspecialUsado;
    }

    public void setUsaChequeEspecial(boolean value){
        this.usaChequeEspecial = value;
    }

    //So deve ser chamado quando o valor do saldo for suficiente para pagar o valor de 20% do cheque
    //OLHAR AQUI
    public boolean cobrarChequeEspecial(){
        BigDecimal debito = this.valorChequeEspecialUsado.multiply(BigDecimal.valueOf(0.2));
        if(this.saldo.compareTo(debito) >= 0){
            this.valorChequeEspecialUsado = this.valorChequeEspecialUsado.subtract(debito);
            if(this.valorChequeEspecialUsado.compareTo(ZERO) == 0){
                this.usaChequeEspecial = false;
            }
            this.saldo = this.saldo.subtract(debito);
            return true;
        }
        
        return false;
    }


//FIM DA CLASSE
}
