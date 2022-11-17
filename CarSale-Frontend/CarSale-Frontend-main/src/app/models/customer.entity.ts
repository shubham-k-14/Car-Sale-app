import { Address } from "./address.entity";

export class Customer{
    constructor(public name:string,public email:string,
        public contactNo:number,public dob:Date,public address:Address[],public role?:string,public password?:string,public userId?:number){}
    }
