package com.ppolodev.iobuilders.moneytokenizer.domain.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class IobToken extends Contract {
    public static final String BINARY = "6080604052348015620000125760006000fd5b50604051620024f5380380620024f58339818101604052810190620000389190620003a1565b5b5b82825b81600360005090805190602001906200005892919062000250565b5080600460005090805190602001906200007492919062000250565b505b505033600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b620000cc3382620000d660201b60201c565b5b505050620006cb565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141515156200014b576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620001429062000483565b60405180910390fd5b6200015f600083836200024a60201b60201c565b806002600082828250546200017591906200053f565b92505081909090555080600060005060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008282825054620001d491906200053f565b9250508190909055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040516200023d9190620004a6565b60405180910390a35b5050565b5b505050565b8280546200025e90620005e0565b90600052602060002090601f016020900481019282620002825760008555620002d3565b82601f106200029d57805160ff1916838001178555620002d3565b82800160010185558215620002d3579182015b82811115620002d25782518260005090905591602001919060010190620002b0565b5b509050620002e29190620002e6565b5090565b620002ec565b80821115620003085760008181506000905550600101620002ec565b509056620006ca565b6000620003286200032284620004f9565b620004c4565b905082815260208101848484011115620003425760006000fd5b6200034f848285620005a8565b505b9392505050565b600082601f83011215156200036d5760006000fd5b81516200037f84826020860162000311565b9150505b92915050565b6000815190506200039a81620006ac565b5b92915050565b60006000600060608486031215620003b95760006000fd5b600084015167ffffffffffffffff811115620003d55760006000fd5b620003e38682870162000358565b935050602084015167ffffffffffffffff811115620004025760006000fd5b620004108682870162000358565b9250506040620004238682870162000389565b9150505b9250925092565b60006200043d601f836200052d565b91507f45524332303a206d696e7420746f20746865207a65726f20616464726573730060008301526020820190505b919050565b6200047c816200059d565b82525b5050565b600060208201905081810360008301526200049e816200042e565b90505b919050565b6000602082019050620004bd600083018462000471565b5b92915050565b6000604051905081810181811067ffffffffffffffff82111715620004ee57620004ed6200067b565b5b80604052505b919050565b600067ffffffffffffffff8211156200051757620005166200067b565b5b601f19601f83011690506020810190505b919050565b60008282526020820190505b92915050565b60006200054c826200059d565b915062000559836200059d565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0382111562000591576200059062000619565b5b82820190505b92915050565b60008190505b919050565b60005b83811015620005c95780820151818401525b602081019050620005ab565b83811115620005d9576000848401525b505b505050565b600060028204905060018216801515620005fb57607f821691505b602082108114156200061257620006116200064a565b5b505b919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b565b620006b7816200059d565b81141515620006c65760006000fd5b5b50565b5b611e1a80620006db6000396000f3fe60806040523480156100115760006000fd5b50600436106100fa5760003560e01c806370a0823111610098578063a457c2d711610067578063a457c2d71461028e578063a9059cbb146102be578063dd62ed3e146102ee578063f2fde38b1461031e576100fa565b806370a0823114610206578063893d20e81461023657806395d89b41146102545780639dc29fac14610272576100fa565b806323b872dd116100d457806323b872dd1461016c578063313ce5671461019c57806339509351146101ba57806340c10f19146101ea576100fa565b806306fdde0314610100578063095ea7b31461011e57806318160ddd1461014e576100fa565b60006000fd5b61010861033a565b6040516101159190611a05565b60405180910390f35b610138600480360381019061013391906114cd565b6103d4565b60405161014591906119e9565b60405180910390f35b610156610403565b6040516101639190611b93565b60405180910390f35b6101866004803603810190610181919061147b565b610415565b60405161019391906119e9565b60405180910390f35b6101a461053f565b6040516101b19190611baf565b60405180910390f35b6101d460048036038101906101cf91906114cd565b61054d565b6040516101e191906119e9565b60405180910390f35b61020460048036038101906101ff91906114cd565b610619565b005b610220600480360381019061021b9190611411565b6106c1565b60405161022d9190611b93565b60405180910390f35b61023e610715565b60405161024b91906119cd565b60405180910390f35b61025c610744565b6040516102699190611a05565b60405180910390f35b61028c600480360381019061028791906114cd565b6107de565b005b6102a860048036038101906102a391906114cd565b610886565b6040516102b591906119e9565b60405180910390f35b6102d860048036038101906102d391906114cd565b61099e565b6040516102e591906119e9565b60405180910390f35b6103086004803603810190610303919061143c565b6109cd565b6040516103159190611b93565b60405180910390f35b61033860048036038101906103339190611411565b610a62565b005b60606003600050805461034c90611d04565b80601f016020809104026020016040519081016040528092919081815260200182805461037890611d04565b80156103c55780601f1061039a576101008083540402835291602001916103c5565b820191906000526020600020905b8154815290600101906020018083116103a857829003601f168201915b505050505090506103d1565b90565b60006103f46103e7610bf363ffffffff16565b8484610c0063ffffffff16565b600190506103fd565b92915050565b60006002600050549050610412565b90565b6000610428848484610ddb63ffffffff16565b6000600160005060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050600061047f610bf363ffffffff16565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050549050828110151515610504576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104fb90611aac565b60405180910390fd5b61052d85610516610bf363ffffffff16565b85846105229190611c40565b610c0063ffffffff16565b600191505061053856505b9392505050565b60006012905061054a565b90565b600061060a610560610bf363ffffffff16565b848460016000506000610577610bf363ffffffff16565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050546105ff9190611be9565b610c0063ffffffff16565b60019050610613565b92915050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106ab576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106a290611b30565b60405180910390fd5b6106bb828261107f63ffffffff16565b5b5b5050565b6000600060005060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050549050610710565b919050565b6000600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050610741565b90565b60606004600050805461075690611d04565b80601f016020809104026020016040519081016040528092919081815260200182805461078290611d04565b80156107cf5780601f106107a4576101008083540402835291602001916107cf565b820191906000526020600020905b8154815290600101906020018083116107b257829003601f168201915b505050505090506107db565b90565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610870576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161086790611b30565b60405180910390fd5b61088082826111e863ffffffff16565b5b5b5050565b600060006001600050600061089f610bf363ffffffff16565b73ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050549050828110151515610964576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161095b90611b51565b60405180910390fd5b61098d610975610bf363ffffffff16565b8585846109829190611c40565b610c0063ffffffff16565b600191505061099856505b92915050565b60006109be6109b1610bf363ffffffff16565b8484610ddb63ffffffff16565b600190506109c7565b92915050565b6000600160005060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050549050610a5c565b92915050565b600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610af4576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610aeb90611b30565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610b315760006000fd5b8073ffffffffffffffffffffffffffffffffffffffff16600560009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600560006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b50565b6000339050610bfd565b90565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151515610c72576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610c6990611b0f565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515610ce4576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610cdb90611a6a565b60405180910390fd5b80600160005060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000508190909055508173ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b92583604051610dcd9190611b93565b60405180910390a35b505050565b600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff1614151515610e4d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610e4490611aee565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614151515610ebf576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610eb690611a28565b60405180910390fd5b610ed08383836113db63ffffffff16565b6000600060005060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050549050818110151515610f5f576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610f5690611a8b565b60405180910390fd5b8181610f6b9190611c40565b600060005060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060005081909090555081600060005060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828282505461100a9190611be9565b9250508190909055508273ffffffffffffffffffffffffffffffffffffffff168473ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040516110709190611b93565b60405180910390a3505b505050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141515156110f1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016110e890611b72565b60405180910390fd5b611103600083836113db63ffffffff16565b806002600082828250546111179190611be9565b92505081909090555080600060005060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082828250546111749190611be9565b9250508190909055508173ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef836040516111db9190611b93565b60405180910390a35b5050565b600073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415151561125a576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161125190611acd565b60405180910390fd5b61126c826000836113db63ffffffff16565b6000600060005060008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000505490508181101515156112fb576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016112f290611a49565b60405180910390fd5b81816113079190611c40565b600060005060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600050819090905550816002600082828250546113669190611c40565b925050819090905550600073ffffffffffffffffffffffffffffffffffffffff168373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040516113cd9190611b93565b60405180910390a3505b5050565b5b50505056611de3565b6000813590506113f481611dad565b5b92915050565b60008135905061140a81611dc8565b5b92915050565b6000602082840312156114245760006000fd5b6000611432848285016113e5565b9150505b92915050565b60006000604083850312156114515760006000fd5b600061145f858286016113e5565b9250506020611470858286016113e5565b9150505b9250929050565b600060006000606084860312156114925760006000fd5b60006114a0868287016113e5565b93505060206114b1868287016113e5565b92505060406114c2868287016113fb565b9150505b9250925092565b60006000604083850312156114e25760006000fd5b60006114f0858286016113e5565b9250506020611501858286016113fb565b9150505b9250929050565b61151581611c75565b82525b5050565b61152581611c88565b82525b5050565b600061153782611bcb565b6115418185611bd7565b9350611551818560208601611ccf565b61155a81611d9b565b84019150505b92915050565b6000611573602383611bd7565b91507f45524332303a207472616e7366657220746f20746865207a65726f206164647260008301527f657373000000000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b60006115da602283611bd7565b91507f45524332303a206275726e20616d6f756e7420657863656564732062616c616e60008301527f636500000000000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b6000611641602283611bd7565b91507f45524332303a20617070726f766520746f20746865207a65726f20616464726560008301527f737300000000000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b60006116a8602683611bd7565b91507f45524332303a207472616e7366657220616d6f756e742065786365656473206260008301527f616c616e6365000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b600061170f602883611bd7565b91507f45524332303a207472616e7366657220616d6f756e742065786365656473206160008301527f6c6c6f77616e636500000000000000000000000000000000000000000000000060208301526040820190505b919050565b6000611776602183611bd7565b91507f45524332303a206275726e2066726f6d20746865207a65726f2061646472657360008301527f730000000000000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b60006117dd602583611bd7565b91507f45524332303a207472616e736665722066726f6d20746865207a65726f20616460008301527f647265737300000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b6000611844602483611bd7565b91507f45524332303a20617070726f76652066726f6d20746865207a65726f2061646460008301527f726573730000000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b60006118ab602583611bd7565b91507f46756e6374696f6e20726573657276656420666f7220636f6e7472616374206f60008301527f776e65722e00000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b6000611912602583611bd7565b91507f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f7760008301527f207a65726f00000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b6000611979601f83611bd7565b91507f45524332303a206d696e7420746f20746865207a65726f20616464726573730060008301526020820190505b919050565b6119b681611cb6565b82525b5050565b6119c681611cc1565b82525b5050565b60006020820190506119e2600083018461150c565b5b92915050565b60006020820190506119fe600083018461151c565b5b92915050565b60006020820190508181036000830152611a1f818461152c565b90505b92915050565b60006020820190508181036000830152611a4181611566565b90505b919050565b60006020820190508181036000830152611a62816115cd565b90505b919050565b60006020820190508181036000830152611a8381611634565b90505b919050565b60006020820190508181036000830152611aa48161169b565b90505b919050565b60006020820190508181036000830152611ac581611702565b90505b919050565b60006020820190508181036000830152611ae681611769565b90505b919050565b60006020820190508181036000830152611b07816117d0565b90505b919050565b60006020820190508181036000830152611b2881611837565b90505b919050565b60006020820190508181036000830152611b498161189e565b90505b919050565b60006020820190508181036000830152611b6a81611905565b90505b919050565b60006020820190508181036000830152611b8b8161196c565b90505b919050565b6000602082019050611ba860008301846119ad565b5b92915050565b6000602082019050611bc460008301846119bd565b5b92915050565b6000815190505b919050565b60008282526020820190505b92915050565b6000611bf482611cb6565b9150611bff83611cb6565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115611c3457611c33611d39565b5b82820190505b92915050565b6000611c4b82611cb6565b9150611c5683611cb6565b925082821015611c6957611c68611d39565b5b82820390505b92915050565b6000611c8082611c95565b90505b919050565b600081151590505b919050565b600073ffffffffffffffffffffffffffffffffffffffff821690505b919050565b60008190505b919050565b600060ff821690505b919050565b60005b83811015611cee5780820151818401525b602081019050611cd2565b83811115611cfd576000848401525b505b505050565b600060028204905060018216801515611d1e57607f821691505b60208210811415611d3257611d31611d6a565b5b505b919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602260045260246000fd5b565b6000601f19601f83011690505b919050565b611db681611c75565b81141515611dc45760006000fd5b5b50565b611dd181611cb6565b81141515611ddf5760006000fd5b5b50565bfea26469706673582212200e71f1b2114c55765c668aea217a2a5ccb926da701d9d2e6257ea1840709ee9d64736f6c63430008000033";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IobToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IobToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IobToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IobToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(String account, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String account, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, sender), 
                new org.web3j.abi.datatypes.Address(160, recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IobToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IobToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IobToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IobToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IobToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IobToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IobToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IobToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IobToken> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String name, String symbol, BigInteger initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply)));
        return deployRemoteCall(IobToken.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<IobToken> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String name, String symbol, BigInteger initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply)));
        return deployRemoteCall(IobToken.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IobToken> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply)));
        return deployRemoteCall(IobToken.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IobToken> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger initialSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint256(initialSupply)));
        return deployRemoteCall(IobToken.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
