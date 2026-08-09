import {Transaction} from './Transaction';

export interface User {
  name: string;
  email: string;
  password: string;
  transactions: Transaction[];
}
