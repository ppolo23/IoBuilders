const { expect } = require('chai');

// Import utilities from Test Helpers
const { BN, ether } = require('@openzeppelin/test-helpers');

// Load compiled artifacts
const IobToken = artifacts.require('IobToken');
const IobCrowdsale = artifacts.require('IobCrowdsale');

// Start test block
contract('IobCrowdsale', function ([ creator, investor, wallet ]) {

  const NAME = 'IobToken';
  const SYMBOL = 'IOB';
  const TOTAL_SUPPLY = new BN('10000000000000000000000');
  const RATE = new BN(10);

  beforeEach(async function () {
    this.token = await IobToken.new(NAME, SYMBOL, TOTAL_SUPPLY, { from: creator });
    this.crowdsale = await IobCrowdsale.new(RATE, wallet, this.token.address);
    this.token.transfer(this.crowdsale.address, await this.token.totalSupply());
  });

  it('should create crowdsale with correct parameters', async function () {
    expect(await this.crowdsale.rate()).to.be.bignumber.equal(RATE);
    expect(await this.crowdsale.wallet()).to.be.equal(wallet);
    expect(await this.crowdsale.token()).to.be.equal(this.token.address);
  });

  it('should accept payments', async function () {
    const investmentAmount = ether('1');
    const expectedTokenAmount = RATE.mul(investmentAmount);

    await this.crowdsale.buyTokens(investor, { value: investmentAmount, from: investor });

    expect(await this.token.balanceOf(investor)).to.be.bignumber.equal(expectedTokenAmount);
  });
});