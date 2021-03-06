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
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class IobWallet extends Contract {
    public static final String BINARY = "6080604052348015620000125760006000fd5b506040516200194e3803806200194e8339818101604052810190620000389190620001e1565b5b5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b6000600060146101000a81548160ff0219169083151502179055505b600082111515620000e1576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401620000d890620002f4565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff161415151562000156576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016200014d90620002d1565b60405180910390fd5b81600260005081909090555080600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5050620003bc56620003bb565b600081519050620001c2816200037f565b5b92915050565b600081519050620001da816200039d565b5b92915050565b6000600060408385031215620001f75760006000fd5b60006200020785828601620001c9565b92505060206200021a85828601620001b1565b9150505b9250929050565b60006200023460248362000317565b91507f496f6257616c6c65743a20746f6b656e20697320746865207a65726f2061646460008301527f726573730000000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b60006200029d60148362000317565b91507f496f6257616c6c65743a2072617465206973203000000000000000000000000060008301526020820190505b919050565b60006020820190508181036000830152620002ec8162000225565b90505b919050565b600060208201905081810360008301526200030f816200028e565b90505b919050565b60008282526020820190505b92915050565b6000620003368262000353565b90505b919050565b60006200034b8262000329565b90505b919050565b600073ffffffffffffffffffffffffffffffffffffffff821690505b919050565b60008190505b919050565b6200038a816200033e565b81141515620003995760006000fd5b5b50565b620003a88162000374565b81141515620003b75760006000fd5b5b50565b5b61158280620003cc6000396000f3fe6080604052600436106100a05760003560e01c80638456cb59116100645780638456cb591461016c578063893d20e81461018457806397304ced146101b0578063a6f2ae3a146101da578063e4849b32146101e4578063f2fde38b1461020e576100a0565b806310fe9ae8146100a65780633f4ba83a146100d25780635c975abb146100ea578063679aefce146101165780636d1b229d14610142576100a0565b60006000fd5b3480156100b35760006000fd5b506100bc610238565b6040516100c991906111e4565b60405180910390f35b3480156100df5760006000fd5b506100e8610267565b005b3480156100f75760006000fd5b506101006103a1565b60405161010d919061128c565b60405180910390f35b3480156101235760006000fd5b5061012c6103bd565b604051610139919061138f565b60405180910390f35b34801561014f5760006000fd5b5061016a60048036038101906101659190610f4b565b6103cf565b005b3480156101795760006000fd5b506101826104f7565b005b3480156101915760006000fd5b5061019a610632565b6040516101a791906111e4565b60405180910390f35b3480156101bd5760006000fd5b506101d860048036038101906101d39190610f4b565b610661565b005b6101e2610789565b005b3480156101f15760006000fd5b5061020c60048036038101906102079190610f4b565b6109e1565b005b34801561021b5760006000fd5b5061023660048036038101906102319190610ef5565b610c86565b005b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050610264565b90565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156102f9576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016102f09061132c565b60405180910390fd5b600060149054906101000a900460ff16151561034a576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610341906112a8565b60405180910390fd5b6000600060146101000a81548160ff0219169083151502179055507f5db9ee0a495bf2e6ff9c91a7834c1ba4fdd244a5e8aa4e537bd38aeae4b073aa3360405161039491906111e4565b60405180910390a15b5b5b565b6000600060149054906101000a900460ff1690506103ba565b90565b600060026000505490506103cc565b90565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610461576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104589061132c565b60405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16639dc29fac30836040518363ffffffff1660e01b81526004016104be929190611262565b600060405180830381600087803b1580156104d95760006000fd5b505af11580156104ee573d600060003e3d6000fd5b505050505b5b50565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610589576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105809061132c565b60405180910390fd5b600060149054906101000a900460ff161515156105db576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105d2906112ea565b60405180910390fd5b6001600060146101000a81548160ff0219169083151502179055507f62e78cea01bee320cd4e420270b5ea74000d11b0c9f74754ebdbfc544b05a2583360405161062591906111e4565b60405180910390a15b5b5b565b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905061065e565b90565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156106f3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106ea9061132c565b60405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166340c10f1930836040518363ffffffff1660e01b8152600401610750929190611262565b600060405180830381600087803b15801561076b5760006000fd5b505af1158015610780573d600060003e3d6000fd5b505050505b5b50565b600060149054906101000a900460ff161515156107db576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107d2906112ea565b60405180910390fd5b60006107ec34610e1763ffffffff16565b9050600081111515610833576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161082a9061136e565b60405180910390fd5b6000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166370a08231306040518263ffffffff1660e01b815260040161089091906111e4565b60206040518083038186803b1580156108a95760006000fd5b505afa1580156108be573d600060003e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108e29190610f76565b9050808211151515610929576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109209061134d565b60405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb33846040518363ffffffff1660e01b8152600401610986929190611262565b602060405180830381600087803b1580156109a15760006000fd5b505af11580156109b6573d600060003e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109da9190610f20565b5050505b5b565b600060149054906101000a900460ff16151515610a33576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a2a906112ea565b60405180910390fd5b600081111515610a78576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a6f906112c9565b60405180910390fd5b6000610a8982610e3d63ffffffff16565b90506000600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663dd62ed3e33306040518363ffffffff1660e01b8152600401610aea929190611200565b60206040518083038186803b158015610b035760006000fd5b505afa158015610b18573d600060003e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610b3c9190610f76565b9050828110151515610b83576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610b7a9061130b565b60405180910390fd5b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3330866040518463ffffffff1660e01b8152600401610be29392919061122a565b602060405180830381600087803b158015610bfd5760006000fd5b505af1158015610c12573d600060003e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610c369190610f20565b503373ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f19350505050158015610c7e573d600060003e3d6000fd5b5050505b5b50565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141515610d18576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610d0f9061132c565b60405180910390fd5b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff1614151515610d555760006000fd5b8073ffffffffffffffffffffffffffffffffffffffff16600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a380600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b50565b6000610e3160026000505483610e6390919063ffffffff16565b9050610e38565b919050565b6000610e5760026000505483610e7e90919063ffffffff16565b9050610e5e565b919050565b60008183610e7191906113f1565b9050610e78565b92915050565b60008183610e8c91906113bd565b9050610e93565b929150505661154b565b600081359050610eac816114fa565b5b92915050565b600081519050610ec281611515565b5b92915050565b600081359050610ed881611530565b5b92915050565b600081519050610eee81611530565b5b92915050565b600060208284031215610f085760006000fd5b6000610f1684828501610e9d565b9150505b92915050565b600060208284031215610f335760006000fd5b6000610f4184828501610eb3565b9150505b92915050565b600060208284031215610f5e5760006000fd5b6000610f6c84828501610ec9565b9150505b92915050565b600060208284031215610f895760006000fd5b6000610f9784828501610edf565b9150505b92915050565b610faa8161144c565b82525b5050565b610fba8161145f565b82525b5050565b6000610fce6014836113ab565b91507f5061757361626c653a206e6f742070617573656400000000000000000000000060008301526020820190505b919050565b600061100f6025836113ab565b91507f596f75206e65656420746f2073656c6c206174206c6561737420736f6d65207460008301527f6f6b656e7300000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b60006110766010836113ab565b91507f5061757361626c653a207061757365640000000000000000000000000000000060008301526020820190505b919050565b60006110b76019836113ab565b91507f436865636b2074686520746f6b656e20616c6c6f77616e63650000000000000060008301526020820190505b919050565b60006110f86025836113ab565b91507f46756e6374696f6e20726573657276656420666f7220636f6e7472616374206f60008301527f776e65722e00000000000000000000000000000000000000000000000000000060208301526040820190505b919050565b600061115f6020836113ab565b91507f4e6f7420656e6f75676820746f6b656e7320696e20746865207265736572766560008301526020820190505b919050565b60006111a0601b836113ab565b91507f596f75206e65656420746f2073656e6420736f6d65206574686572000000000060008301526020820190505b919050565b6111dd8161148d565b82525b5050565b60006020820190506111f96000830184610fa1565b5b92915050565b60006040820190506112156000830185610fa1565b6112226020830184610fa1565b5b9392505050565b600060608201905061123f6000830186610fa1565b61124c6020830185610fa1565b61125960408301846111d4565b5b949350505050565b60006040820190506112776000830185610fa1565b61128460208301846111d4565b5b9392505050565b60006020820190506112a16000830184610fb1565b5b92915050565b600060208201905081810360008301526112c181610fc1565b90505b919050565b600060208201905081810360008301526112e281611002565b90505b919050565b6000602082019050818103600083015261130381611069565b90505b919050565b60006020820190508181036000830152611324816110aa565b90505b919050565b60006020820190508181036000830152611345816110eb565b90505b919050565b6000602082019050818103600083015261136681611152565b90505b919050565b6000602082019050818103600083015261138781611193565b90505b919050565b60006020820190506113a460008301846111d4565b5b92915050565b60008282526020820190505b92915050565b60006113c88261148d565b91506113d38361148d565b92508215156113e5576113e46114c9565b5b82820490505b92915050565b60006113fc8261148d565b91506114078361148d565b9250817fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff04831182151516156114405761143f611498565b5b82820290505b92915050565b60006114578261146c565b90505b919050565b600081151590505b919050565b600073ffffffffffffffffffffffffffffffffffffffff821690505b919050565b60008190505b919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601260045260246000fd5b565b6115038161144c565b811415156115115760006000fd5b5b50565b61151e8161145f565b8114151561152c5760006000fd5b5b50565b6115398161148d565b811415156115475760006000fd5b5b50565bfea2646970667358221220dc6bc04cd5276f457b30e3e05c10dfca26063395f4b8e013cfdefd62c220a84164736f6c63430008000033";

    public static final String FUNC_BURNTOKENS = "burnTokens";

    public static final String FUNC_BUY = "buy";

    public static final String FUNC_GETOWNER = "getOwner";

    public static final String FUNC_GETRATE = "getRate";

    public static final String FUNC_GETTOKENADDRESS = "getTokenAddress";

    public static final String FUNC_MINTTOKENS = "mintTokens";

    public static final String FUNC_PAUSE = "pause";

    public static final String FUNC_PAUSED = "paused";

    public static final String FUNC_SELL = "sell";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_UNPAUSE = "unpause";

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAUSED_EVENT = new Event("Paused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event UNPAUSED_EVENT = new Event("Unpaused", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected IobWallet(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IobWallet(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IobWallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IobWallet(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
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

    public List<PausedEventResponse> getPausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PAUSED_EVENT, transactionReceipt);
        ArrayList<PausedEventResponse> responses = new ArrayList<PausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PausedEventResponse typedResponse = new PausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PausedEventResponse>() {
            @Override
            public PausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PAUSED_EVENT, log);
                PausedEventResponse typedResponse = new PausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PausedEventResponse> pausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAUSED_EVENT));
        return pausedEventFlowable(filter);
    }

    public List<UnpausedEventResponse> getUnpausedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(UNPAUSED_EVENT, transactionReceipt);
        ArrayList<UnpausedEventResponse> responses = new ArrayList<UnpausedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UnpausedEventResponse typedResponse = new UnpausedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, UnpausedEventResponse>() {
            @Override
            public UnpausedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(UNPAUSED_EVENT, log);
                UnpausedEventResponse typedResponse = new UnpausedEventResponse();
                typedResponse.log = log;
                typedResponse.account = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<UnpausedEventResponse> unpausedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UNPAUSED_EVENT));
        return unpausedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> burnTokens(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURNTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BUY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<String> getOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getRate() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getTokenAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOKENADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mintTokens(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINTTOKENS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> pause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> paused() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAUSED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> sell(BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(amount)), 
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

    public RemoteFunctionCall<TransactionReceipt> unpause() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNPAUSE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IobWallet load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IobWallet(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IobWallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IobWallet(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IobWallet load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IobWallet(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IobWallet load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IobWallet(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IobWallet> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, BigInteger rate, String token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(160, token)));
        return deployRemoteCall(IobWallet.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<IobWallet> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, BigInteger rate, String token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(160, token)));
        return deployRemoteCall(IobWallet.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IobWallet> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(160, token)));
        return deployRemoteCall(IobWallet.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<IobWallet> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger rate, String token) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rate), 
                new org.web3j.abi.datatypes.Address(160, token)));
        return deployRemoteCall(IobWallet.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PausedEventResponse extends BaseEventResponse {
        public String account;
    }

    public static class UnpausedEventResponse extends BaseEventResponse {
        public String account;
    }
}
