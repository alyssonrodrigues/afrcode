export const required = value => (value ? undefined : 'Obrigatório')

export const maxLength = max => value =>
  value && value.length > max ? `Deve ter ${max} caracteres ou menos` : undefined

export const minLength = min => value =>
  value && value.length < min ? `Deve ter ${min} caracteres ou mais` : undefined

export const number = value =>
  value && isNaN(Number(value)) ? 'Deve ser um número' : undefined

export const minValue = min => value =>
  value && value < min ? `Deve ser maior ou igual a ${min}` : undefined

export const email = value =>
  value && !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(value)
    ? 'Endereço de email inválido'
    : undefined

export const alphaNumeric = value =>
  value && /[^a-zA-Z0-9]/i.test(value)
    ? 'Deve conter apenas caracteres alfanuméricos'
    : undefined
