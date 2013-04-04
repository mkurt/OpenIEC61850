/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openiec61850.jmms.iso.acse.asn1;

import java.io.IOException;
import java.io.InputStream;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;

public class Myexternal {

	public static class SubChoice_encoding {

		public byte[] code = null;
		public BerAny single_ASN1_type = null;

		public BerOctetString octet_aligned = null;

		public BerBitString arbitrary = null;

		public SubChoice_encoding() {
		}

		public SubChoice_encoding(byte[] code) {
			this.code = code;
		}

		public SubChoice_encoding(BerAny single_ASN1_type, BerOctetString octet_aligned, BerBitString arbitrary) {
			this.single_ASN1_type = single_ASN1_type;
			this.octet_aligned = octet_aligned;
			this.arbitrary = arbitrary;
		}

		public int encode(BerByteArrayOutputStream berOStream, boolean explicit) throws IOException {
			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					berOStream.write(code[i]);
				}
				return code.length;

			}
			int codeLength = 0;
			int sublength;

			if (arbitrary != null) {
				codeLength += arbitrary.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)).encode(berOStream);
				return codeLength;

			}
			
			if (octet_aligned != null) {
				codeLength += octet_aligned.encode(berOStream, false);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)).encode(berOStream);
				return codeLength;

			}
			
			if (single_ASN1_type != null) {
				sublength = single_ASN1_type.encode(berOStream, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(berOStream, sublength);
				codeLength += (new BerIdentifier(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 0)).encode(berOStream);
				return codeLength;

			}
			
			throw new IOException("Error encoding BerChoice: No item in choice was selected.");
		}

		public int decode(InputStream iStream, BerIdentifier berIdentifier) throws IOException {
			int codeLength = 0;
			int choiceDecodeLength = 0;
			BerIdentifier passedIdentifier = berIdentifier;
			if (berIdentifier == null) {
				berIdentifier = new BerIdentifier();
				codeLength += berIdentifier.decode(iStream);
			}
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 0)) {
				BerLength tempLength = new BerLength();
				codeLength += tempLength.decode(iStream);
				codeLength += tempLength.val;
				return codeLength;
			}

			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
				octet_aligned = new BerOctetString();
				codeLength += octet_aligned.decode(iStream, false);
				return codeLength;
			}

			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)) {
				arbitrary = new BerBitString();
				codeLength += arbitrary.decode(iStream, false);
				return codeLength;
			}

			if (passedIdentifier != null) {
				return 0;
			}
			throw new IOException("Error decoding BerChoice: Identifier matched to no item.");
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			BerByteArrayOutputStream berOStream = new BerByteArrayOutputStream(encodingSizeGuess);
			encode(berOStream, false);
			code = berOStream.getArray();
		}
	}

	public final static BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 8);
	protected BerIdentifier id;

	public byte[] code = null;
	public BerObjectIdentifier direct_reference = null;

	public BerInteger indirect_reference = null;

	public SubChoice_encoding encoding = null;

	public Myexternal() {
		id = identifier;
	}

	public Myexternal(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public Myexternal(BerObjectIdentifier direct_reference, BerInteger indirect_reference, SubChoice_encoding encoding) {
		id = identifier;
		this.direct_reference = direct_reference;
		this.indirect_reference = indirect_reference;
		this.encoding = encoding;
	}

	public int encode(BerByteArrayOutputStream berOStream, boolean explicit) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				berOStream.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			codeLength += encoding.encode(berOStream, true);
			
			if (indirect_reference != null) {
				codeLength += indirect_reference.encode(berOStream, true);
			}
			
			if (direct_reference != null) {
				codeLength += direct_reference.encode(berOStream, true);
			}
			
			codeLength += BerLength.encodeLength(berOStream, codeLength);
		}

		if (explicit) {
			codeLength += id.encode(berOStream);
		}

		return codeLength;

	}

	public int decode(InputStream iStream, boolean explicit) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		int choiceDecodeLength = 0;
		BerIdentifier berIdentifier = new BerIdentifier();
		boolean decodedIdentifier = false;

		if (explicit) {
			codeLength += id.decodeAndCheck(iStream);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(iStream);

		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			if (berIdentifier.equals(BerObjectIdentifier.identifier)) {
				direct_reference = new BerObjectIdentifier();
				subCodeLength += direct_reference.decode(iStream, false);
				decodedIdentifier = false;
			}
		}
		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			if (berIdentifier.equals(BerInteger.identifier)) {
				indirect_reference = new BerInteger();
				subCodeLength += indirect_reference.decode(iStream, false);
				decodedIdentifier = false;
			}
		}
		if (subCodeLength < length.val) {
			if (decodedIdentifier == false) {
				subCodeLength += berIdentifier.decode(iStream);
				decodedIdentifier = true;
			}
			encoding = new SubChoice_encoding();
			choiceDecodeLength = encoding.decode(iStream, berIdentifier);
			if (choiceDecodeLength != 0) {
				decodedIdentifier = false;
				subCodeLength += choiceDecodeLength;
			}
		}
		if (subCodeLength != length.val) {
			throw new IOException("Decoded sequence has wrong length tag");

		}
		codeLength += subCodeLength;

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream berOStream = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(berOStream, false);
		code = berOStream.getArray();
	}
}
