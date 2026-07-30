import {User} from './User';

export interface Transaction {
  id: string;
  name: string;
  description: string;
  owner: User;
  transactionType: string;
  category: string;
  amount: bigint;
  transactionDate: string
}
