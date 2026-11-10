# Projetos de padrões de projeto

O projeto contém três exemplos independentes em Java:

- **Adapter** — adaptação de interfaces para cotação de frete.
- **Prototype** — cópia de pedidos.
- **Strategy** — estratégias de cálculo de frete.

Execute os comandos abaixo no PowerShell, a partir da pasta do projeto. É necessário ter o JDK instalado.

## Adapter

```powershell
javac -d out\adapter src\adapter\*.java
java -cp out\adapter adapter.Main
```

## Prototype

```powershell
javac -d out\pedidos src\pedidos\*.java
java -cp out\pedidos pedidos.PedidosApp
```

## Strategy

```powershell
javac -d out\strategy src\strategy\*.java
java -cp out\strategy strategy.Main
```
