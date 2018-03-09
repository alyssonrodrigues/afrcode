import React from 'react';

function DeletedHobby(props) {
  return (
    <li 
      onClick={() => props.readdHobby(props.hobby)} 
      style={{color: 'red'}}>
      {props.hobby}
    </li>
  );
}

export default DeletedHobby;