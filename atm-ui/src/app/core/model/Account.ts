import { UserResponse } from './UserResponse';
import { Bank } from './Bank';


export interface Account {
    id: number;
    number: number;
    owner: UserResponse;
    balance: number;
    password: string;
    bank: Bank;
}