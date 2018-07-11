import React from 'react'
import { shallow } from 'enzyme'

import { UserMenu } from './UserMenu'

describe('UserMenu', () => {
  it('UserMenu render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <UserMenu classes={{}} theme={{}} userMenuAnchorEl={'id'}
        authentication={{isUserAuthenticated: true, username: 'josejoao'}}
        handleUserMenuOpen={jest.fn()} handleUserMenuClose={jest.fn()} />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
