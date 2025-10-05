import java.math.BigDecimal;

public class Conta {

    private BigDecimal saldo;
    private final BigDecimal chequeEspecial;

    private boolean usaChequeEspecial = false;

    private BigDecimal definirChequeEspecial(BigDecimal saldoInicial){
        final BigDecimal QUINHENTOS = BigDecimal.valueOf(500);
        boolean condicao = saldoInicial.compareTo(QUINHENTOS) >= 0; //cheque de 50%
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

    public BigDecimal getChequeEspecial(){
        return this.chequeEspecial;
    }

    public void depositar(BigDecimal valor){
        BigDecimal saldoAtual = this.saldo;
        this.saldo = saldoAtual.add(valor);
    }

    public boolean sacar(BigDecimal valor){
        BigDecimal saldoAtual = this.saldo;

        if(saldoAtual.compareTo(valor) < 0){ //Se o saldo atual não for sufiente para o saque

            BigDecimal diferenca = valor.subtract(saldoAtual);

            //Se o valor que precisa para completar puder ser emprestado
            if(!this.usaChequeEspecial && diferenca.compareTo(this.chequeEspecial) <= 0){
                this.saldo = saldoAtual.add(this.chequeEspecial).subtract(valor);
                this.usaChequeEspecial = true;
                return true;
            }else{return false;}

        }else{
            this.saldo = saldoAtual.subtract(valor);
            return true;
        }

    }

    public boolean getUsaChequeEspecial(){
        return this.usaChequeEspecial;
    }

    //So deve ser chamado quando o valor do saldo for suficiente para pagar o valor de 20% do cheque
    //Olhar novamente isso
    public boolean cobrarChequeEspecial(){
        BigDecimal saldo = this.saldo;
        BigDecimal valor = this.chequeEspecial;
        if(saldo.compareTo(valor) >= 0){
            this.saldo = saldo.subtract(valor);
            this.usaChequeEspecial = false;

            return true;
        }

        return false;
    }


//FIM DA CLASSE
}
