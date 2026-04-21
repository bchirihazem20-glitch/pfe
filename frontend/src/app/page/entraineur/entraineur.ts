import { Component, OnInit } from '@angular/core';
import { UserService } from '../../service/userService';

@Component({
  selector: 'app-entraineur',
templateUrl: './entraineur.component.html',
styleUrls: ['./entraineur.component.css']
})
export class EntraineurComponent implements OnInit {

  users: any[] = [];

  user: any = {
    id: null,
    nom: '',
    prenom: '',
    email: '',
    telephone: ''
  };

  isEdit = false;

  constructor(private service: UserService) {}

  ngOnInit(): void {
    this.load();
  }

  // 🔥 GET COACHS
  load() {
    this.service.getCoachs().subscribe((res) => {
      this.users = res;
    });
  }

  // 🔥 SAVE (ADD or UPDATE)
  save() {
    console.log("USER BEFORE SAVE:", this.user); // 🔥 debug

    if (this.isEdit && this.user.id != null) {
      this.service.update(this.user.id, this.user).subscribe(() => {
        this.load();
        this.reset();
      });
    } else {
      this.service.add(this.user).subscribe(() => {
        this.load();
        this.reset();
      });
    }
  }

  // 🔥 EDIT
  edit(u: any) {
    console.log("EDIT USER:", u); // 🔥 debug

    this.user = {
      id: u.id,
      nom: u.nom,
      prenom: u.prenom,
      email: u.email,
      telephone: u.telephone
    };

    this.isEdit = true;
  }

  // 🔥 DELETE
  delete(id: number) {
    this.service.delete(id).subscribe(() => {
      this.load();
    });
  }

  // 🔥 RESET
  reset() {
    this.user = {
      id: null,
      nom: '',
      prenom: '',
      email: '',
      telephone: ''
    };
    this.isEdit = false;
  }
}