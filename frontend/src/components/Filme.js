import React from "react";
import { Table, Button, Form, Modal } from "react-bootstrap";
import { BASE_URL } from "./resquests";
import "./styles.css";

import { MdDelete } from "react-icons/md";
import { BiEditAlt } from "react-icons/bi";

class Filme extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: 0,
      nome: "",
      categoria: "",
      moment: "",
      filmes: [],
      modalAberta: false,
    };
  }

  componentDidMount() {
    this.buscarFilmes();
  }

  componentWillUnmount() {}

  buscarFilmes = () => {
    fetch(`${BASE_URL}/api/filmes`)
      .then((resposta) => resposta.json())
      .then((dados) => {
        this.setState({ filmes: dados });
      });
  };

  deletarFilme = (id) => {
    fetch(`${BASE_URL}/api/filmes/` + id, { method: "DELETE" }).then(
      (resposta) => {
        if (resposta.ok) {
          alert("Filme deletado com sucesso!");
          this.buscarFilmes();
        } else {
          alert("Não foi possivel deletar");
        }
      }
    );
    console.log(this.deletarFilme);
  };

  carregarDados = (id) => {
    fetch(`${BASE_URL}/api/filmes/` + id, { method: "GET" })
      .then((resposta) => resposta.json())
      .then((filme) => {
        this.setState({
          id: filme.id,
          nome: filme.nome,
          categoria: filme.categoria,
        });
        this.abrirModal();
      });
  };

  cadastrarFilme = (filme) => {
    fetch(`${BASE_URL}/api/filmes`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(filme),
    }).then((resposta) => {
      if (resposta.ok) {
        this.buscarFilmes();
        alert("Filme cadastrado com sucesso!");
      } else {
        alert("Nome do filme já cadastrado.");
      }
    });
  };

  atualizarFilme = (filme) => {
    fetch(`${BASE_URL}/api/filmes/` + filme.id, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(filme),
    }).then((resposta) => {
      if (resposta.ok) {
        this.buscarFilmes();
        alert("Filme atualizado com sucesso!");
      }
    });
  };

  renderTabela() {
    return (
      <Table striped bordered hover className="red">
        <thead className="teste">
          <tr>
            <th>Nome</th>
            <th>Categoria</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {this.state.filmes.map((filme) => (
            <tr>
              <td>{filme.nome}</td>
              <td>{filme.categoria}</td>
              <td>
                <BiEditAlt
                  className="btn_atualizar"
                  variant="success"
                  onClick={() => this.carregarDados(filme.id)}
                ></BiEditAlt>

                <MdDelete
                  className="btn_delete"
                  variant="danger"
                  onClick={() => this.deletarFilme(filme.id)}
                ></MdDelete>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    );
  }

  atualizaNome = (e) => {
    this.setState({
      nome: e.target.value,
    });
  };

  atualizaCategoria = (e) => {
    this.setState({
      categoria: e.target.value,
    });
  };

  submit = () => {
    if (this.state.id === 0) {
      const filme = {
        nome: this.state.nome,
        categoria: this.state.categoria,
      };
      this.cadastrarFilme(filme);
    } else {
      const filme = {
        id: this.state.id,
        nome: this.state.nome,
        categoria: this.state.categoria,
      };
      this.atualizarFilme(filme);
    }
    this.fecharModal();
  };

  reset = () => {
    this.setState({
      id: 0,
      nome: "",
      categoria: "",
    });
    this.abrirModal();
  };

  fecharModal = () => {
    this.setState({
      modalAberta: false,
    });
  };

  abrirModal = () => {
    this.setState({
      modalAberta: true,
    });
  };

  render() {
    return (
      <div>
        <Modal show={this.state.modalAberta} onHide={this.fecharModal}>
          <Modal.Header closeButton>
            <Modal.Title>Modal heading</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form>
              <Form.Group className="mb-3">
                <Form.Label>ID</Form.Label>
                <Form.Control
                  type="text"
                  value={this.state.id}
                  readOnly={true}
                />
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Nome</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Nome do filme"
                  value={this.state.nome}
                  onChange={this.atualizaNome}
                />
                <Form.Text className="text-muted">
                  É o que você faz agora que faz a diferença.(Falcão Negro em
                  Perigo).
                </Form.Text>
              </Form.Group>

              <Form.Group className="mb-3">
                <Form.Label>Categoria</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Categoria do Filme"
                  value={this.state.categoria}
                  onChange={this.atualizaCategoria}
                />
              </Form.Group>
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={this.fecharModal}>
              Close
            </Button>

            <Button variant="primary" type="submit" onClick={this.submit}>
              Salvar
            </Button>
          </Modal.Footer>
        </Modal>

        <Button className="test" onClick={this.reset}>
          Novo
        </Button>
        <p></p>
        {this.renderTabela()}
      </div>
    );
  }
}

export default Filme;
