import { saveSecurityToken,
  removeSecurityToken,
  getAuthentication } from './securityContext'

describe('securityContext', () => {
  it('O tokenJwt deveria ter sido salvo sem falhas!', () => {
    const authentication = {
      tokenJwt: 'tokenJwt',
      username: 'alyssonfr'
    }
    expect(saveSecurityToken(authentication)).toEqual(authentication)
  })

  it('O tokenJwt deveria ter sido removido sem falhas!', () => {
    const authentication = {
      tokenJwt: 'tokenJwt',
      username: 'alyssonfr'
    }
    saveSecurityToken(authentication)
    removeSecurityToken()
    expect(getAuthentication()).toEqual(null)
  })

  it('O tokenJwt deveria ter sido obtido sem falhas!', () => {
    const authentication = {
      tokenJwt: 'tokenJwt',
      username: 'alyssonfr'
    }
    saveSecurityToken(authentication)
    expect(getAuthentication()).toEqual(authentication)
  })
})
