export class ArmyDomain {

  constructor(
    public id: string,
    public name: string
  ) {}

  toString() {
    return JSON.stringify(this);
  }

}
