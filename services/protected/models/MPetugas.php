<?php

/**
 * This is the model class for table "m_petugas".
 *
 * The followings are the available columns in table 'm_petugas':
 * @property string $kode_petugas
 * @property string $kode_prov
 * @property string $kode_kab
 * @property string $nama_petugas
 * @property string $status
 * @property string $jabatan
 * @property string $flag
 * @property string $kode_kortim
 * @property string $token
 * @property string $email
 * @property string $blank1
 * @property string $password
 *
 * The followings are the available model relations:
 * @property MBs[] $mBs
 * @property TUser[] $tUsers
 * @property MKab $kodeKab
 * @property MKab $kodeProv
 * @property MPetugas $kodeKortim
 * @property MPetugas[] $mPetugases
 * @property TRt[] $tRts
 */
class MPetugas extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MPetugas the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}

	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return 'm_petugas';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_petugas, kode_prov, kode_kab, nama_petugas', 'required'),
			array('kode_petugas, kode_kortim', 'length', 'max'=>9),
			array('kode_prov, kode_kab', 'length', 'max'=>2),
			array('nama_petugas, token, email, blank1, password', 'length', 'max'=>255),
			array('status, jabatan, flag', 'length', 'max'=>1),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_petugas, kode_prov, kode_kab, nama_petugas, status, jabatan, flag, kode_kortim, token, email, blank1, password', 'safe', 'on'=>'search'),
		);
	}

	/**
	 * @return array relational rules.
	 */
	public function relations()
	{
		// NOTE: you may need to adjust the relation name and the related
		// class name for the relations automatically generated below.
		return array(
			'mBs' => array(self::HAS_MANY, 'MBs', 'kode_petugas'),
			'tUsers' => array(self::HAS_MANY, 'TUser', 'kode_petugas'),
			'kodeKab' => array(self::BELONGS_TO, 'MKab', 'kode_kab'),
			'kodeProv' => array(self::BELONGS_TO, 'MKab', 'kode_prov'),
			'kodeKortim' => array(self::BELONGS_TO, 'MPetugas', 'kode_kortim'),
			'mPetugases' => array(self::HAS_MANY, 'MPetugas', 'kode_kortim'),
			'tRts' => array(self::HAS_MANY, 'TRt', 'kode_petugas'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'kode_petugas' => 'Kode Petugas',
			'kode_prov' => 'Kode Prov',
			'kode_kab' => 'Kode Kab',
			'nama_petugas' => 'Nama Petugas',
			'status' => 'Status',
			'jabatan' => 'Jabatan',
			'flag' => 'Flag',
			'kode_kortim' => 'Kode Kortim',
			'token' => 'Token',
			'email' => 'Email',
			'blank1' => 'Blank1',
			'password' => 'Password',
		);
	}

	/**
	 * Retrieves a list of models based on the current search/filter conditions.
	 * @return CActiveDataProvider the data provider that can return the models based on the search/filter conditions.
	 */
	public function search()
	{
		// Warning: Please modify the following code to remove attributes that
		// should not be searched.

		$criteria=new CDbCriteria;

		$criteria->compare('kode_petugas',$this->kode_petugas,true);
		$criteria->compare('kode_prov',$this->kode_prov,true);
		$criteria->compare('kode_kab',$this->kode_kab,true);
		$criteria->compare('nama_petugas',$this->nama_petugas,true);
		$criteria->compare('status',$this->status,true);
		$criteria->compare('jabatan',$this->jabatan,true);
		$criteria->compare('flag',$this->flag,true);
		$criteria->compare('kode_kortim',$this->kode_kortim,true);
		$criteria->compare('token',$this->token,true);
		$criteria->compare('email',$this->email,true);
		$criteria->compare('blank1',$this->blank1,true);
		$criteria->compare('password',$this->password,true);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}