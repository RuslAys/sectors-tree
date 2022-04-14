import {Button, Container, Row} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import React from "react";
import {Link} from "react-router-dom";

function MainPage() {

    return (
        <div className="d-flex align-items-center justify-content-center mt-lg-5">
            <Container fluid="md">
                <Row className="justify-content-md-center">
                    <h1>Welcome!</h1>
                </Row>
                <Row className="justify-content-md-center">
                    <div className="border d-flex align-items-center">
                        <Link to="/register">
                            <Button variant="outline-primary" className="m-1">Register user sectors</Button>
                        </Link>
                        <Link to="/manage">
                            <Button variant="outline-info" className="m-1">Manage sectors</Button>
                        </Link>
                    </div>
                </Row>
            </Container>
        </div>
    );
}

export default MainPage;
