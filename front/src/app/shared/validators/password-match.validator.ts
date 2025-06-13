import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export function passwordValidator(): ValidatorFn{
    return (form: AbstractControl): ValidationErrors | null => {
            const password = form.get('password')?.value;
            const confirmPassword = form.get("confirmPassword")?.value;
            return password && confirmPassword && password !== confirmPassword
            ? {passwordMisMatch: true} : null
        }
}