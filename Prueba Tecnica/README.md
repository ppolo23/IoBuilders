# IoBuilders Technical test - Statement

Develop a backend with Java and Sping boot applying hexagonal architecture for the organization and structuring of the files.
Also, have one or more smart contracts (ERC 20) deployed on an Ethereum node.
Incorporate some special / incremental functionality according to my considerations to the ERC 20.

Basic functionalities:

- User creation.
- Money operations: deposit, transfer, withdraw...

# Resolution

The resolution of this test consists of two parts:

1. The Backend
2. The Smart Contracts

## 1. The Backend

The Backend has been developed using Java, Spring Boot and Web3j, and it´s in charge of receiving the request sent by the users and performing the corresponding operations.

In order to meet the requirements, I decided to develop some users logic (user creation and consult) and provide the application the apropiate end points for interacting with the smart contracts (buy, sell, withdraw).

Apart for those functions, I developed the method `deposit`, wich is in charge of providing the users Ether so that they can perform blockchain operations. As i worked with a local testnet wich creates a number  of accounts with eth, I chose one of them be act as a facuted (as some other testnets do).

### Requests

Those are the requests accepted by the system:

- User creation:
```bash
curl -X POST -d "username=user4&password=pwd" http://localhost:8081/user
```

- Get all users:
```bash
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8081/user/all
```

- Get user by name:
```bash
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8081/user/user1
```

- Deposit
```bash
curl -X POST http://localhost:8081/user/user1/deposit/10
```

- Buy
```bash
curl -X POST http://localhost:8081/user/user1/buyIobTokens/10
```

- Withdraw
```bash
curl -X POST http://localhost:8081/user/user1/withdraw/5
```

- Transfer
```bash
curl -X POST http://localhost:8081/user/user1/transfer/5/user2
```

## 2. Smart Contracts

For this part, I developed two main Smart Contracts: IobToken and IobWallet.

IobToken is my ERC20 token for this task. It just imports the ERC20 token implementation from Openzeppelin and adds some functionallity for token creation and burn. This last methods are only executable by the owner of the contract.

IobWallet is the Smart Contract in charge of managing the IobToken. This way, users are able to buy and sell tokens through this contract. Furthermore, I made this contract pausable by its owner, so that users can only buy and sell whenever the contract is not paused, and I add methods for token minting and burning (only executable by contracts owner).

The idea is that IobWallet owns the IobToken so that the is a unique address where users can buy and sell their tokens from.