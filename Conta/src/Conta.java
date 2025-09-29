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
        BigDecimal chequeEspecial = this.chequeEspecial;
        BigDecimal valorAtual = saldoAtual;
        if(saldoAtual.compareTo(valor) < 0){
            //precisa do cheque especial
            BigDecimal disponibilidadeChequeEspecial = chequeEspecial.subtract(valorChequeEspecialUsado);
            if(disponibilidadeChequeEspecial.compareTo(ZERO) > 0){
                valorAtual = saldoAtual.add(chequeEspecial);
                this.valorChequeEspecialUsado = valor.subtract(saldoAtual);
                this.usaChequeEspecial = true;
            }
        }

        boolean ehPossivel = valorAtual.compareTo(valor) >= 0; //dá pra sacar
        if(ehPossivel){
            this.saldo = valorAtual.subtract(valor);
            return true;
        }

        return false;
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


//FIM DA CLASSE
}
