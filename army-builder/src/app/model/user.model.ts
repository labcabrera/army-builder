export class User {

  constructor(
    public id: string,
    public name: string,
    public roles?: string[]
  ) {}

  toString() {
    return JSON.stringify(this);
  }

}
