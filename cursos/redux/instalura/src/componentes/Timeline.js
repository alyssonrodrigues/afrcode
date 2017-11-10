import React, {Component} from 'react';
import Foto from './Foto';
import {withRouter} from 'react-router-dom';
import TimelineApi from '../api/TimelineApi';
import {connect} from 'react-redux';

class Timeline extends Component {
    componentDidMount() {
        if (localStorage.getItem('auth-token') === null) {
            this.props.history.push('/?msg=É necessário fazer o login...');
            return;
        }

        let url = `http://localhost:8080/api/fotos?X-AUTH-TOKEN=${localStorage.getItem('auth-token')}`;
        if (this.props.match.params.user) {
            url = `http://localhost:8080/api/public/fotos/${this.props.match.params.user}`;
        }
        this.props.lista(url);
    }

    render() {
        return (
            <div className="fotos container">
                {
                    this.props.fotos.map(foto =>
                        <Foto key={foto.id} foto={foto} like={this.props.like} comenta={this.props.comenta}/>
                    )
                }
            </div>
        );
    }
}

const mapStateToProps = state => {
    return {fotos: state.timeline};
};

const mapDispatchToProps = dispatch => {
    return {
        like: (fotoId) => {
            dispatch(TimelineApi.like(fotoId));
        },
        comenta: (fotoId, textoComentario) => {
            dispatch(TimelineApi.comenta(fotoId, textoComentario))
        },
        lista: (url) => {
            dispatch(TimelineApi.lista(url));
        }
    };
};

const TimelineContainer = connect(mapStateToProps, mapDispatchToProps)(Timeline);

export default withRouter(TimelineContainer);