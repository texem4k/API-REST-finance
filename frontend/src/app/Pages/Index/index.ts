import {Component, OnInit} from '@angular/core';
import {User} from '../../Model/User';
import {Transaction} from '../../Model/Transaction';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './index.html',
  styleUrl: './styles.css',
})

export class Index implements OnInit {

  users: User[] | undefined;
  transactions: Transaction[] | undefined;


  ngOnInit(): void {
  }
}
