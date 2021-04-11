const IobToken = artifacts.require("IobToken");
const IobWallet = artifacts.require("IobWallet");

module.exports = async function (deployer, network, accounts) {
  //Token deployment
  await deployer.deploy(IobToken, 'Iob Token', 'IOB', '10000000000000000000000');
  const token = await IobToken.deployed();
  
  //Wallet deployment
  await deployer.deploy(IobWallet, 1, token.address);
  const wallet = await IobWallet.deployed();

  //Setting ownerShip and transfering total supply to wallet
  await token.transferOwnership(wallet.address);
  await token.transfer(wallet.address, await token.totalSupply());
};