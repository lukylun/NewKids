import { atom } from 'recoil';
import { IMember } from 'types/auth';

export const MemberInfoState = atom<IMember | null>({
	key: 'memberInfoState',
	default: null,
});
