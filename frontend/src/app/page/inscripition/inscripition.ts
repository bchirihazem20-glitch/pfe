import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, AbstractControl, ValidationErrors } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../service/auth/auth';
import { GroupesService } from '../../service/groupes.service';

@Component({
  selector: 'app-inscripition',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule, MatIconModule],
  templateUrl: './inscripition.html',
  styleUrl: './inscripition.css'
})
export class Inscripition implements OnInit {

  registerForm!: FormGroup;
  showPassword = false;
  showConfirmPassword = false;
  maxDate!: string;

  groupes: any[] = [];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private groupesService: GroupesService
  ) {}

  ngOnInit(): void {
    this.maxDate = new Date().toISOString().split('T')[0];

    this.registerForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      role: ['', Validators.required],
      groupId: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      adresse: [''],
      telephone: [''],
      dateNaissance: ['', [Validators.required, this.minAgeValidator(4)]]
    }, {
      validators: this.passwordMatchValidator
    });

    this.loadGroupes();
  }

  get f() {
    return this.registerForm.controls;
  }

  loadGroupes(): void {
    this.groupesService.getAllGroupes().subscribe({
      next: (data: any[]) => {
        this.groupes = data;
        console.log('GROUPES :', this.groupes);
      },
      error: (err: any) => {
        console.error('Erreur chargement groupes :', err);
      }
    });
  }

  passwordMatchValidator(form: AbstractControl): ValidationErrors | null {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      form.get('confirmPassword')?.setErrors({ mismatch: true });
      return { mismatch: true };
    }

    return null;
  }

  minAgeValidator(minAge: number) {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!control.value) return null;

      const birthDate = new Date(control.value);
      const today = new Date();

      let age = today.getFullYear() - birthDate.getFullYear();
      const month = today.getMonth() - birthDate.getMonth();

      if (month < 0 || (month === 0 && today.getDate() < birthDate.getDate())) {
        age--;
      }

      return age >= minAge ? null : { minAge: true };
    };
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  toggleConfirmPassword(): void {
    this.showConfirmPassword = !this.showConfirmPassword;
  }

<<<<<<< HEAD
  onSubmit(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    const formValue = this.registerForm.value;

    const payload = {
      nom: `${formValue.nom} ${formValue.prenom}`,
      email: formValue.email,
      password: formValue.password,
      role: formValue.role,
      dateNaissance: formValue.dateNaissance,
      groupId: Number(formValue.groupId)
    };

    console.log('REGISTER PAYLOAD :', payload);

    this.authService.register(payload).subscribe({
      next: (res) => {
        console.log('Inscription réussie', res);
      },
      error: (err) => {
        console.error('Erreur inscription :', err);
      }
    });
=======
  get f() {
    return this.registerForm.controls;
  }

  onSubmit() {
  if (this.registerForm.valid) {
    this.authService.register(this.registerForm.value).subscribe({
      next: () => {
        alert('✅ Inscription réussie !');
        this.registerForm.reset();
      },
      error: (err) => {
        console.log(err);

        // 🔥 cas email déjà utilisé
        if (err.status === 409 || err.error?.message?.includes('email')) {
          alert('❌ Cet email est déjà utilisé. Veuillez en choisir un autre.');
        } 
        // 🔥 autre erreur
        else {
          alert('❌ Erreur lors de l’inscription !');
        }
      }
    });
  } else {
    this.registerForm.markAllAsTouched();
>>>>>>> aae08d83d8169ac5916fa657ee2f22d4f97420cb
  }
}
}