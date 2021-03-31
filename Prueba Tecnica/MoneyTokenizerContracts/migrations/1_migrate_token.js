const IoToken = artifacts.require("IoToken");

module.exports = function(deployer) {
  deployer.deploy(IoToken, 1000);
};
