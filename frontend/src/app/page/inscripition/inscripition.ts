
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth/auth';

@Component({
  selector: 'app-register',
  imports:[CommonModule,ReactiveFormsModule],
templateUrl: './inscripition.html',
  styleUrl: './inscripition.css',
})
export class  Inscripition implements OnInit {

  
  registerForm!: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      nom: ['', [Validators.required, Validators.minLength(2)]],
      prenom: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      adresse: ['', Validators.required],
      telephone: ['', [Validators.required, Validators.pattern('^[0-9]{8,10}$')]],
      dateNaissance: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: AbstractControl) {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    if (password !== confirmPassword) {
      form.get('confirmPassword')?.setErrors({ mismatch: true });
    } else {
      form.get('confirmPassword')?.setErrors(null);
    }
  }

  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
    if (this.registerForm.valid) {
      // envoyer les données à l'API
      this.authService.register(this.registerForm.value).subscribe({
        next: (res) => {
          console.log('Inscription réussie:', res);
          alert('Inscription réussie !');
          // éventuellement redirection après inscription
        },
        error: (err) => {
          console.error('Erreur inscription:', err);
          alert('Erreur lors de l’inscription !');
        }
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }

}