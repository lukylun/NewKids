import styled from 'styled-components';

export const CheckBoxWrapper = styled.div`
	margin-top: 1.5rem;

	input[id='input'] + label {
		font-size: 2rem;
		width: 100px;
		height: 100px;
	}
	input[id='input']:checked + label {
		font-size: 2rem;
		background-color: var(--sub-color-1);
	}
    input[id='input] {
        display: none
    }
`;
