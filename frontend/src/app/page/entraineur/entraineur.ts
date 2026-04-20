import { Component, OnInit } from '@angular/core';
import { EntraineurService } from './entraineur.spec';

@Component({
  selector: 'app-entraineur',
  templateUrl: './entraineur.component.html',
  styleUrls: ['./entraineur.component.css']
})
export class EntraineurComponent implements OnInit {

  entraineurs: any[] = [];
  entraineur = {
    id: null,
    nom: '',
    prenom: '',
    specialite: '',
    email: '',
    telephone: ''
  };

  isEdit = false;

  constructor(private service: EntraineurService) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.getAll().subscribe(res => {
      this.entraineurs = res;
    });
  }

  save() {
    if (this.isEdit) {
      this.service.update(this.entraineur.id, this.entraineur).subscribe(() => {
        this.load();
        this.reset();
      });
    } else {
      this.service.add(this.entraineur).subscribe(() => {
        this.load();
        this.reset();
      });
    }
  }

  edit(e: any) {
    this.entraineur = { ...e };
    this.isEdit = true;
  }

  delete(id: number) {
    this.service.delete(id).subscribe(() => {
      this.load();
    });
  }

  reset() {
    this.entraineur = {
      id: null,
      nom: '',
      prenom: '',
      specialite: '',
      email: '',
      telephone: ''
    };
    this.isEdit = false;
  }
}