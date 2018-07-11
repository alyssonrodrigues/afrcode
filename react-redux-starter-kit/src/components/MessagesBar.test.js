import React from 'react'
import { shallow } from 'enzyme'

import { MessagesBar } from './MessagesBar'

describe('MessagesBar', () => {
  it('MessagesBar render deveria ocorrer sem falhas!', () => {
    const componentWrapper = shallow(
      <MessagesBar classes={{}} messages={{message: 'Olá mundo!'}} dismissMessage={jest.fn()} />
    )
    expect(componentWrapper).toMatchSnapshot()
  })
})
