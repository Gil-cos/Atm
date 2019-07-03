import { Bill } from './Bill';

export interface WithdrawDto {
    bills: Bill[];
    message: string;
}