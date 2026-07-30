import {Transaction} from './Transaction';

export interface User {
  id: string;
  username: string;
  email: string;
  registerDate: string;
  transactionList: Transaction[];
}
