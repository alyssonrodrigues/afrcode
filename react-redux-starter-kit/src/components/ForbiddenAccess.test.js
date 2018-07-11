import React from 'react'
import { shallow } from 'enzyme'

import { ForbiddenAccess } from './ForbiddenAccess'

describe('ForbiddenAccess', () => {
  it('ForbiddenAccess render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <ForbiddenAccess classes={{}} dismissMessage={jest.fn()} />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
