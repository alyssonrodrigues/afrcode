import React, {Component} from 'react';
import Foto from './Foto';
import {withRouter} from 'react-router-dom';

export class Timeline extends Component {
    constructor() {
        super();
        this.state = {fotos: []};
    }

    componentDidMount() {
        if (localStorage.getItem('auth-token') === null) {
            this.props.history.push('/?msg=É necessário fazer o login...');
            return;
        }

        let url = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
        if (this.props.match.params.user) {
            url = `http://localhost:8080/api/public/fotos/${this.props.match.params.user}`;
        }
        fetch(url)
            .then(response => response.json())
            .then(fotos => {
                this.setState({fotos: fotos});
            });
    }

    render() {
        return (
            <div className="fotos container">
                {
                    this.state.fotos.map(foto => <Foto key={foto.id} foto={foto}/>)
                }
            </div>
        );
    }
}

export default withRouter(Timeline);