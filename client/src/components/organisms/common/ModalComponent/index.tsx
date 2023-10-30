import React, { ReactNode } from 'react';
import { ModalComponentContainer } from './style';

interface IModalComponentProps {
	children: ReactNode;
}

function ModalComponent(props: IModalComponentProps) {
	const { children } = props;

	return (
		<ModalComponentContainer>
			<div className="modal-body" role="presentation">
				{children}
			</div>
		</ModalComponentContainer>
	);
}

export default ModalComponent;
