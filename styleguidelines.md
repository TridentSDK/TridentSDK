TridentSDK Style Guidelines
====
This is the document that specifies the required style format to be used in development of TridentSDK.

We follow a loose [Oracle Code Conventions](http://www.oracle.com/technetwork/java/codeconvtoc-136057.html) style.

## Format

- 4 space indentation. Do not use tabs.
- 80 character limit per line
- [Egyption Brackets](http://blog.codinghorror.com/new-programming-jargon/)
- Copyright appears at the very top of the file, followed by whitespace, followed by the package.
- No whitespace between first field declaration and class declaration
- Always include ```@author The TridentSDK Team``` in the class javadoc
- Indent as specified in §4 of the Oracle Code Conventions (link above).
- No whitespace after closing brace of class, no whitespace before closing brace.
- New line after ```;```, do not put multiple statements on one line
- Keywords and braces have whitespace around them
- All operators have surrounding whitespace
- All ```for``` statements have whitespace after the ```;```, always have whitespace after commas ```,```.

## Naming

- Managers or handling classes are named ```{Function}Handler```
- Package names are always lowercase. No exceptions.
- **NEVER** use underscores ```_``` except for ```CONSTANT_FIELDS```.
- Use shortened field names sparingly
- Abbreviations allowed for local variables
- Classes start with a capital, each word starts with a capital. No all CAPS class names.
- Interfaces have the same standard as class names
- Constants are ```{modifier} static final``` and all CAPS named, underscores between words.
- Methods and fields use lowercase first letter, and capitalized first letter for every following word

## Technical Design

- Thread safe classes use static factories
- Immutable classes marked with ```@ThreadSafe```
- Use ```@GuardedBy``` annotations if synchronization is used
- **API METHODS MUST BE THREAD SAFE**
- Fields made final whenever it possible
- Initialize as soon as possible, unless object contains heavy computation. Mark for discussion.

## Documentation

- Description, whitespace, then tags
- Follow §5 of the Oracle Code Conventions (link above).
- Only field comments are allowed to be single line comments
- Overriden methods do not need documentation, as long as the superclass/superinterface is documented
- Classes are always documented unless they are package private
- Package private classes are marked with ```@AccessNoDoc```. Do not document them.
- ```@InternalUseOnly``` members are not documented. Do not use on API classes.
- New packages must include a ```package-info.html``` to describe/list classes in that package
- API documentation should not include implementation details
- Use ```@Volatile``` to document an unstable or non-conventional usage of a particular member
- Prefer ```@throws``` over ```@exception```, and ```@code``` over ```<code>```.
- Link whenever necessary
- ```@code``` java keywords
- Document return of null, or possible usage of null in parameters

## Practices
- Use ```this``` qualifiers for method calls or field references
- Do not qualify ```this``` for inner classes, as it generates ```{Enclosing class}.this.{reference}```
- Static qualifiers are not needed if used inside the class
- Do not use parentheses if not necessary
- If exceptions are ignored, explain why
- Use ```TridentLogger.error(Exception)``` when handling exceptions
