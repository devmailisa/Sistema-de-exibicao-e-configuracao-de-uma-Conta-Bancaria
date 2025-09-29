import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Conta conta = criarContaBancaria();

    static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
    static DecimalFormat df = new DecimalFormat("R$ #,##0.00", symbols);

    public static void main(String[] args){
        System.out.println("Olá! Seja bem vindo ao sistema de criação de conta");
        while(true){
            menu();
            int operacao = scanner.nextInt();
            switch (operacao){
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    consultarChequeEspecial();
                    break;
                case 3:
                    depositarDinheiro();
                    break;
                case 4:
                    sacarDinheiro();
                    break;
                case 5:
                    pagarBoleto();
                    break;
                case 6:
                    break;
                case 7:
                    exit();
            }

        }


    }


    public static void menu(){
        System.out.println("Olá, seja bem-vindo(a) a sua Conta Bancária.");
        System.out.println("Qual operação você gostaria da fazer?");
        System.out.println("(1) Consultar saldo");
        System.out.println("(2) Consultar cheque especial");
        System.out.println("(3) Depositar dinheiro");
        System.out.println("(4) Sacar dinheiro");
        System.out.println("(5) Pagar um boleto");
        System.out.println("(6) Verificar se a conta está usando cheque especial");
        System.out.println("(7) Sair");
    }

    public static void exit(){
        System.exit(0);
    }

    public static Conta criarContaBancaria(){
        System.out.println("Olá! Seja bem vindo ao nosso sistema de conta bancária!");
        System.out.println("Agora, iremos criar a sua conta bancária:");
        System.out.println("Qual é o valor inicial do saldo? ");
        BigDecimal saldo = scanner.nextBigDecimal();
        return new Conta(saldo);
    }

    public static void consultarSaldo(){
        System.out.printf("Saldo atual: %s", df.format(conta.getSaldo()));
    }

    public static void consultarChequeEspecial(){
        System.out.printf("Valor do cheque especial: %s", df.format(conta.getChequeEspecial()));
    }

    public static void depositarDinheiro(){
        System.out.println("Valor que você quer depositar:");
        BigDecimal deposito = scanner.nextBigDecimal();
        conta.depositar(deposito);
    }

    public static void sacarDinheiro(){
        System.out.println("Valor que você quer sacar:");
        BigDecimal saque = scanner.nextBigDecimal();
        boolean ehPossivel = conta.sacar(saque);
        if(ehPossivel){
            System.out.println("Saque realizado com sucesso.");
        }else{
            System.out.println("Saldo insuficiente. ");
        }

        consultarSaldo();
    }

    public static void pagarBoleto(){
        System.out.println("Valor do boleto:");
        BigDecimal valorDoBoleto = scanner.nextBigDecimal();
        boolean ehPossivel = conta.sacar(valorDoBoleto);
        if(ehPossivel){
            System.out.println("Boleto pago com sucesso.");
        }else{
            System.out.println("Saldo insuficiente. ");
        }

        consultarSaldo();
    }

    public static void verificarChequeEspecial(){
        if(conta.getUsaChequeEspecial()){
            System.out.println("O cheque especial está sendo utilizado.");
            System.out.printf("Valor utilizado: %s", df.format(conta.getValorChequeEspecialUsado()));
        }
    }
}