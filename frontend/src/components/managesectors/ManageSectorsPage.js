import {connect} from "react-redux";
import {getSectors} from "../../actions/sectors.action";
import {Col, Container, Row} from "react-bootstrap";
import React from "react";
import DeleteSectorsForm from "./DeleteSectorsForm";
import ManageSectorsForm from "./ManageSectorsForm";

function ManageSectorsPage({sectors, getSectors}) {

    const [selectedNode, setSelectedNode] = React.useState()

    React.useEffect(() => {
        getSectors()
    }, [])

    const onDeleteNode = () => {
        getSectors();
    }

    const onSaveNode = () => {
        getSectors();
    }

    const onUpdateNode = () => {
        getSectors();
    }

    const onSelectedNode = (value) => {
        setSelectedNode(value)
    }

    return (
        <Container fluid>
            <Row>
                <Col>
                    <DeleteSectorsForm roots={sectors} onDelete={onDeleteNode} onSelectNode={onSelectedNode}/>
                </Col>
                <Col>
                    <ManageSectorsForm selectedSector={selectedNode} roots={sectors} onSave={onSaveNode}
                                       onUpdate={onUpdateNode}/>
                </Col>
            </Row>
        </Container>
    )
}

const mapStateToProps = state => ({
    sectors: state.sectors.sectors
});

export default connect(mapStateToProps, {
    getSectors
})(ManageSectorsPage);