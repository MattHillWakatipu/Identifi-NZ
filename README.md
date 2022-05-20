# Identifi-NZ

My prototype consists of an android app that demonstrates some of the key functionality required for a decenetralised digital identity solution. 
Particularly, it leverages the decentralised hypermedia protocol IPFS to store identity information (identity fragments) immutably. 
Additionally, due to the lack of built-in pagination for IPFS Gateway’s, the data stored here is unable to be viewed without the content ID. 
This provides a compromise between privacy and usability similar to an unlisted Youtube video.

In regards to current identity solutions, centralised models of identity are not without their advantages. 
Most government agencies are trustworthy in the sense that they will apply the same rules and regulations to average citizens. 
That, when combined with the high amount of fraud detection technology contained within objects such as passports, 
allows operators who accept governmental identification a high degree of certainty that the documents are genuine. 
This level of  acceptability is difficult to match with decentralized systems and likely will only culminate after a radical shift in perspective.

The assumption of universal trustworthiness of centralised models is itself at the heart of the problem (Goodell & Aste, 2019), 
as even if such an entity were ethically infallible a singular point of trust can easily lead to a singular point of failure. 
These centralised models also hold both risks of theft of the authentication data stored by identity providers, as well as considerable privacy concerns, 
as agencies that provide identity services can track user activity (Mir et al., 2022). 
This is particularly concerning when used in combination with Online Advertising platforms such as Google’s AdSense, 
as large amounts of incredibly accurate data could be harvested from users of said systems without their consent (Cucchietti et al., 2022).

When considering a similar case of identification the usage of passwords, and password managers as an example of centralised digital systems we see 
similar ethical questions being posed. Recent studies conducted by Alodhyani et al (2020) found that low levels of adoption for password managers 
despite their increase in security do not seem related to user experience. Instead, they suggest that issues surrounding trust and transparency are 
the primary cause of a lack of adoption (Alodhyani et al., 2020). This reinforces the argument that solutions for digital identity should, be trust-less, 
transparent, and decentralized, once again suggesting that a privacy-focused blockchain would be a good fit for this project.

When speaking to the wider context that self-sovereign identity fits into, Ana Beduschi’s (2019) work provides a strong case for systems that are not 
reliant on traditional documentation such as birth certificates and passports. Their work highlights that a lack of formal documentation, 
which is common in the developing world or amongst asylum seekers, compromises an individual’s human rights to equal treatment due to the difficulties 
faced with access to identity verification.

Digital solutions have a unique position in that they can readily leverage developments in biometric data such as fingerprints and iris scans. 
By utilizing these innate methods of identification rather than constructed ones like birth certificates, an identity solution would facilitate an 
equal opportunity to services such as banks or education which individuals without formal documentation would not typically be allowed
access to (Beduschi, 2019).

## References

Alodhyani, F., Theodorakopoulos, G., & Reinecke, P. (2020). Password Managers—It’s All about Trust and Transparency. Future Internet, 12(11), 189. https://doi.org/10.3390/fi12110189

Beduschi, A. (2019). Digital identity: Contemporary challenges for data protection, privacy and non-discrimination rights. Big Data & Society, 6(2), 2053951719855091. https://doi.org/10.1177/2053951719855091

Cucchietti, F., Moll, J., Esteban, M., Reyes, P., & García Calatrava, C. (2022). _carbolytics, an analysis of the carbon costs of online tracking. http://carbolytics.org/report.html

Goodell, G., & Aste, T. (2019). A Decentralized Digital Identity Architecture. Frontiers in Blockchain, 2, 17. https://doi.org/10.3389/fbloc.2019.00017

Mir, O., Roland, M., & Mayrhofer, R. (2022). Decentralized, Privacy-Preserving, Single Sign-On. Security and Communication Networks, 2022, 1–18. https://doi.org/10.1155/2022/9983995
