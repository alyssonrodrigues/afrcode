import React, { Component } from 'react';

import AddedHobby from './AddedHobby';
import DeletedHobby from './DeletedHobby';

class Hobbies extends Component {
  constructor(props) {
    super(props);
    this.state = {
      hobbies: ['Running', 'Dancing', 'Singing'],
      deletedHobbies: [],
      newHobby: ''
    };
  }

  addNewHobby() {
    this.setState(
      prevState => {
        return {
          hobbies: prevState.hobbies.concat(prevState.newHobby),
          newHobby: ''
        }
      }
    );
  }

  deleteHobby(hobby) {
    this.setState(
      prevState => {
        return {
          hobbies: prevState.hobbies.filter(h => h !== hobby),
          deletedHobbies: prevState.deletedHobbies.concat(hobby)
        }
      }
    );
  }

  readdHobby(deletedHobby) {
    this.setState(
      prevState => {
        return {
          hobbies: prevState.hobbies.concat(deletedHobby),
          deletedHobbies: prevState.deletedHobbies.filter(h => h !== deletedHobby)
        }
      }
    );
  }

  setNewHobby(event) {
    this.setState({
      newHobby: event.target.value
    });
  }

  render() {
    return (
      <div id="hobbies">
        <input type="text" value={this.state.newHobby} 
          onChange={this.setNewHobby.bind(this)}/>
        <button onClick={this.addNewHobby.bind(this)}>Add new hobby</button>

        {
          this.state.hobbies.length ? 
            <p>New hobbies: {this.state.hobbies.length}</p> : ''
        }

        <ul>
          {
            this.state.hobbies.map(
              hobby => 
                <AddedHobby 
                  key={hobby}
                  hobby={hobby} 
                  deleteHobby={this.deleteHobby.bind(this)}/>
            )
          }
        </ul>

        {
          this.state.deletedHobbies.length ? 
            <p>Old hobbies: {this.state.deletedHobbies.length}</p> : ''
        }

        <ul>
          {
            this.state.deletedHobbies.map(
              deletedHobby => 
                <DeletedHobby 
                  key={deletedHobby}
                  hobby={deletedHobby} 
                  readdHobby={this.readdHobby.bind(this)} />
            )
          }
        </ul>
      </div>
    );
  }
}

export default Hobbies;
