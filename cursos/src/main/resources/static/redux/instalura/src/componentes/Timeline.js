import React, {Component} from 'react';
import Foto from './Foto';

export default class Timeline extends Component {
    constructor() {
        super();
        this.state = {fotos: []};
    }

    componentDidMount() {
        fetch('http://localhost:8080/api/public/fotos/vitor')
            .then(response => response.json())
            .then(fotos => {
                this.setState({fotos: fotos});
            });
    }

    render() {
        return (
            <div className="fotos container">
                {
                    this.state.fotos.map(foto => <Foto foto={foto}/>)
                }
            </div>
        );
    }
}