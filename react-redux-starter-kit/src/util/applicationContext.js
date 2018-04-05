export const codRecursoComputacional = 201;

export const authenticationUrl = process.env.NODE_ENV === 'production' ?
  'http://autenticacao-rest-prod.cloudapps.tcu.gov.br/auth/token-jwt' :
  'http://autenticacao-rest.desenv.rancher.tcu.gov.br/auth/token-jwt';

export const createDataToAuthentication = (username, password) => ({
  codRecursoComputacional,
  username,
  password
});