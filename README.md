# Sistema de Exibição e Configuração de uma Conta Bancária em Java 💰
Essa atividade visa exercitar os conhecimentos básicos do *Paradigma de Programação Orientada a Objeto* (POO), com ênfase nas premissas de *encapsulamento* e *abstração*. Ela foi introduzida no *bootcamp* da DIO "Riachuelo - Primeiros Passos com Java".

---

## 🧠 Objetivo do Projeto

O objetivo é criar uma classe que represente uma **conta bancária**, permitindo ao usuário realizar operações comuns como depósitos, saques e pagamentos, além de consultar o saldo e o uso do cheque especial.  

A proposta reforça a importância do **encapsulamento** — protegendo os atributos internos da classe — e da **abstração**, expondo ao usuário apenas os comportamentos essenciais do sistema bancário.

---

## ⚙️ Funcionalidades

A conta bancária permite realizar as seguintes operações:

- **Consultar saldo**
- **Consultar limite do cheque especial**
- **Depositar dinheiro**
- **Sacar dinheiro**
- **Pagar boleto**
- **Verificar se a conta está utilizando o cheque especial**

---

## 💡 Regras de Negócio

1. A conta possui **saldo** e **limite de cheque especial**.  
   O **cheque especial é somado ao saldo total disponível** para movimentações.

2. O **valor do cheque especial** é definido **no momento da criação da conta**, de acordo com o valor inicial depositado:
   - Se o depósito inicial for **menor ou igual a R$ 500,00**, o limite do cheque especial será **R$ 50,00**;
   - Se o depósito inicial for **maior que R$ 500,00**, o limite será de **50% do valor depositado**.

3. As operações de **saque** e **pagamento de boleto** devem verificar se há **saldo suficiente** (incluindo cheque especial).

4. Caso o valor do saque for maior que o saldo, significa que o **cheque especial pode ser ativado**.

---

## 🧩 Conceitos Aplicados

### 🔒 Encapsulamento
Os atributos da conta (como saldo e limite) são **privados**, sendo acessados apenas por **métodos públicos** de leitura e modificação.  
Isso garante maior **segurança** e **controle sobre o estado** do objeto.

### 🧱 Abstração
A classe **ContaBancaria** representa uma **abstração** do mundo real, expondo apenas os métodos necessários para interação com a conta, sem revelar detalhes internos de implementação.

---

## 🧾 Estrutura do Projeto

