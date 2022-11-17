import { Card } from "src/app/Card";
export class Payment{
    constructor(
        public type:string,
        public status:string,
        public card : Card
    ){}
}
