const IobToken = artifacts.require("IobToken");
const IobCrowdsale = artifacts.require("IobCrowdsale");

module.exports = async function (deployer, network, accounts) {
  await deployer.deploy(IobToken, 'Iob Token', 'IOB', '10000000000000000000000');
  const token = await IobToken.deployed();
  
  await deployer.deploy(IobCrowdsale, 1, accounts[0], token.address);
  const crowdsale = await IobCrowdsale.deployed();

  await token.transfer(crowdsale.address, await token.totalSupply());
};