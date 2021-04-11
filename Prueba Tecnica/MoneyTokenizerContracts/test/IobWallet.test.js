const { expect } = require('chai');

// Import utilities from Test Helpers
const { BN, ether } = require('@openzeppelin/test-helpers');

// Load compiled artifacts
const IobToken = artifacts.require('IobToken');
const IobWallet = artifacts.require('IobWallet');

// Start test block
contract('IobWallet', function ([ owner, investor, account ]) {

  const NAME = 'IobToken';
  const SYMBOL = 'IOB';
  const TOTAL_SUPPLY = new BN('10000000000000000000000');
  const RATE = new BN(1);

  beforeEach(async function () {
    this.token = await IobToken.new(NAME, SYMBOL, TOTAL_SUPPLY, { from: owner });
    this.wallet = await IobWallet.new(RATE, this.token.address, { from: owner });

    this.token.transferOwnership(this.wallet.address, {from: owner});
    this.token.transfer(this.wallet.address, await this.token.totalSupply());
  });

  it('should create wallet with correct parameters', async function () {
    expect(await this.wallet.getRate()).to.be.bignumber.equal(RATE);
    expect(await this.wallet.getTokenAddress()).to.be.equal(this.token.address);
  });

  it('should let owner mint more tokens', async function () {
    const newSupply = new BN('10');
    const newBalance = TOTAL_SUPPLY.add(newSupply);
    await this.wallet.mintTokens(newSupply, {from: owner})
    expect(await this.token.balanceOf(this.wallet.address)).to.be.bignumber.equal(newBalance);
  });

  it('should let owner burn tokens', async function () {
    const newSupply = new BN('10');
    const newBalance = TOTAL_SUPPLY.sub(newSupply);
    await this.wallet.burnTokens(newSupply, {from: owner});
    expect(await this.token.balanceOf(this.wallet.address)).to.be.bignumber.equal(newBalance);
  });

  it('should let owner pause wallet', async function () {
    await this.wallet.pause({from: owner});
    expect(await this.wallet.paused()).to.be.true;
  });

  it('should let owner unpause wallet', async function () {
    await this.wallet.pause({from: owner});
    await this.wallet.unpause({from: owner});
    expect(await this.wallet.paused()).to.be.false;
  });

  it('should accept payments', async function () {
    const investmentAmount = ether('1');
    const expectedTokenAmount = RATE.mul(investmentAmount);

    await this.wallet.buy({ value: investmentAmount, from: investor });

    expect(await this.token.balanceOf(investor)).to.be.bignumber.equal(expectedTokenAmount);
  });

  it('should accept sales', async function () {
    //Buying tokens
    const investmentAmount = ether('1');
    const tokenAmount = RATE.mul(investmentAmount);
    await this.wallet.buy({ value: investmentAmount, from: investor });

    //Selling some of them
    const saleAmount = new BN('100');
    const expectedTolenBalance = tokenAmount.sub(saleAmount);

    await this.token.increaseAllowance(this.wallet.address, saleAmount, {from: investor});
    await this.wallet.sell(saleAmount, { from: investor });

    expect(await this.token.balanceOf(investor)).to.be.bignumber.equal(expectedTolenBalance);
  });
});