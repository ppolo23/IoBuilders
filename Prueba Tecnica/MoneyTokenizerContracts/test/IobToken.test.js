const { expect } = require('chai');

// Import utilities from Test Helpers
const { BN } = require('@openzeppelin/test-helpers');

// Load compiled artifacts
const IobToken = artifacts.require('IobToken');

// Start test block
contract('IobToken', function ([ creator ]) {

  const NAME = 'IobToken';
  const SYMBOL = 'IOB';
  const TOTAL_SUPPLY = new BN('10000');

  beforeEach(async function () {
    this.token = await IobToken.new(NAME, SYMBOL, TOTAL_SUPPLY, { from: creator });
  });

  it('has a total supply', async function () {
    // Use large integer comparisons
    expect(await this.token.totalSupply()).to.be.bignumber.equal(TOTAL_SUPPLY);
  });

  it('has a name', async function () {
    expect(await this.token.name()).to.be.equal(NAME);
  });

  it('has a symbol', async function () {
    expect(await this.token.symbol()).to.be.equal(SYMBOL);
  });

  it('assigns the initial total supply to the creator', async function () {
    expect(await this.token.balanceOf(creator)).to.be.bignumber.equal(TOTAL_SUPPLY);
  });

  it('increases total supply properly', async function () {
    const newSupply = new BN('100');
    const total = TOTAL_SUPPLY.add(newSupply);
    await this.token.mint(creator, newSupply);
    
    expect(await this.token.balanceOf(creator)).to.be.bignumber.equal(total);
  });

  it('decreasses total supply properly', async function () {
    const newSupply = new BN('100');
    const total = TOTAL_SUPPLY.sub(newSupply);
    await this.token.burn(creator, newSupply);

    expect(await this.token.balanceOf(creator)).to.be.bignumber.equal(total);
  });
});