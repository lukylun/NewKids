import { styled } from 'styled-components';
import { CheckTextButtonStyles } from 'styles/styles';

interface ICheckTextButtonWrapperProps {
	$value: boolean;
	$size: 's' | 'm' | 'l' | 'xl';
}

export const CheckTextButtonWrapper = styled.div<ICheckTextButtonWrapperProps>`
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 5px;
	color: ${({ $value }) => ($value ? 'var(--main-color)' : 'var(--gray-400)')};
	width: fit-content;

	svg {
		fill: ${({ $value }) => ($value ? 'var(--main-color)' : 'var(--gray-400)')};
	}

	${({ $size }) => CheckTextButtonStyles[$size]}

	&:hover {
		cursor: pointer;
	}
`;
