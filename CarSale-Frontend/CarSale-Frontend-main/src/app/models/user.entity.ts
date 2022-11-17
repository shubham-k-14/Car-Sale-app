export class User {
  constructor(
    public role: string,
    public password: string,
    public userId?: number,
    public token?: string
  ) {}
}
