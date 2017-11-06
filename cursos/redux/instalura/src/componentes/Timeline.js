import React, {Component} from 'react';
import Foto from './Foto';
import {withRouter} from 'react-router-dom';
import Pubsub from 'pubsub-js';

export class Timeline extends Component {
    constructor(props) {
        super(props);
        this.state = {fotos: []};
        this.login = this.props.login;
    }

    componentWillMount() {
        this.props.store.subscribe(fotos => {
            this.setState({fotos});
        });
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
        this.props.store.lista(url);
    }

    like(fotoId) {
        this.props.store.like(fotoId);
    }

    comenta(fotoId, comentario) {
        this.props.store.comenta(fotoId,comentario);
    }

    render() {
        return (
            <div className="fotos container">
                {
                    this.state.fotos.map(foto =>
                        <Foto key={foto.id} foto={foto} like={this.like.bind(this)} comenta={this.comenta.bind(this)}/>
                    )
                }
            </div>
        );
    }
}

export default withRouter(Timeline);