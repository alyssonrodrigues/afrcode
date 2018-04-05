import MockAdapter from 'axios-mock-adapter';

import { axiosWrapper,
  USER_AUTHENTICATED,
  USER_LOGOUT,
  AUTHENTICATION_FAILED,
  authenticateUser,
  logoutUser } from './authenticationJwtActions';
import { getAuthentication } from '../security/securityContext';
import { authenticationUrl, createDataToAuthentication } from '../util/applicationContext';

it('A autenticação deveria ter falhado!', () => {
  const data = createDataToAuthentication('alyssonfr', '123456');
  const responseData = '404 error message...';

  const axiosMock = new MockAdapter(axiosWrapper);
  axiosMock.onPost(authenticationUrl).reply(404, responseData);

  const authFn = authenticateUser(data);
  const dispatchFn = jest.fn(action => {
    expect(action.type).toEqual(AUTHENTICATION_FAILED);
    expect(action.payload.err.response.data).toEqual(responseData);
  });
  authFn(dispatchFn);
});

it('A autenticação deveria ter ocorrido sem falhas!', () => {
  const data = createDataToAuthentication('alyssonfr', 'teste');
  const tokenJwt = {
    tokenJwt: 'tokenJwt'
  };

  const axiosMock = new MockAdapter(axiosWrapper);
  axiosMock.onPost(authenticationUrl).reply(200, tokenJwt);

  const authFn = authenticateUser(data);
  const dispatchFn = jest.fn(action => {
    const authentication = getAuthentication();
    expect(authentication.tokenJwt).toEqual(tokenJwt.tokenJwt);
    expect(authentication.username).toEqual(data.username);
    expect(authentication.isUserAuthenticated).toEqual(true);

    expect(action.type).toEqual(USER_AUTHENTICATED);
    expect(action.payload.tokenJwt).toEqual(tokenJwt.tokenJwt);
    expect(action.payload.username).toEqual(data.username);
    expect(action.payload.isUserAuthenticated).toEqual(true);
  });
  authFn(dispatchFn);
});

it('O logout de usuário deveria ter ocorrido sem falhas!', () => {
  const action = logoutUser();
  expect(getAuthentication()).toEqual(null);

  expect(action.type).toEqual(USER_LOGOUT);
  expect(action.payload.tokenJwt).toEqual(null);
  expect(action.payload.username).toEqual(null);
  expect(action.payload.isUserAuthenticated).toEqual(false);
});