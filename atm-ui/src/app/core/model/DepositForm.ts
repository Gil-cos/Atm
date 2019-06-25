export class DepositForm {
    constructor(
        public bankName: string,
        public accountNumber: number,
        public password: number,
        public value: number

    ) { }
}