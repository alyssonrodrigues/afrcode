import React from 'react'
import ReactDOM from 'react-dom'
import './index.css'
import Root from './Root'
import { unregister } from './registerServiceWorker'

import './interceptors'

ReactDOM.render(<Root />, document.getElementById('root'))
unregister()
